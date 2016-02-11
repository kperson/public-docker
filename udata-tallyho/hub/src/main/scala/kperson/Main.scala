package kperson

import akka.actor.{Actor, Props}
import akka.io.IO

import spray.can.Http

import udata.directory.Directory
import udata.{HubServerConfig, HubActorSystem}
import udata.hub.HubServer

case class ServerArguments(host: String = "0.0.0.0", port: Int = 8080, bucket: String = "") {

  def bucketName = Option(System.getenv("AWS_BUCKET")).getOrElse(bucket)
  def isValid = !bucketName.isEmpty

}

trait MainHubApp {

  def start(args: Array[String]) {
    def parser() = new scopt.OptionParser[ServerArguments]("hub") {

      head("udata", "0.1")

      opt[String]("host") action { (x, c) =>
        c.copy(host = x)
      } text ("the host the server will bind to")

      opt[Int]("port") action { (x, c) =>
        c.copy(port = x)
      } text ("the port the server will bind to")

      opt[String]("bucket") action { (x, c) =>
        c.copy(bucket = x)
      } text ("the s3 bucket")
    }

    parser().parse(args, ServerArguments()) match {
      case Some(config) if config.isValid =>
        MyDirectory.s3Bucket = config.bucketName

        implicit val system = HubActorSystem.system

        val serverConfig = new HubServerConfig()

        val directory = Class.forName(serverConfig.directoryManagerClassName).newInstance().asInstanceOf[Directory]
        val lockProps = Props(Class.forName(serverConfig.lockManagerClassName).asInstanceOf[Class[Actor]])
        val pubSubProps = Props(Class.forName(serverConfig.pubSubManagerClassName).asInstanceOf[Class[Actor]])
        val queueProps = Props(Class.forName(serverConfig.queueManagerClassName).asInstanceOf[Class[Actor]])
        val countProps = Props(Class.forName(serverConfig.countManagerClassName).asInstanceOf[Class[Actor]])

        val handler = system.actorOf(Props(
          new HubServer(
            directory,
            lockProps,
            pubSubProps,
            queueProps,
            countProps
          )).withDispatcher("akka.pubsub-dispatcher"))
        IO(Http) ! Http.Bind(handler, interface = config.host, port = config.port)
      case _ => println("--help for details")
    }
  }

}

object Main extends App with MainHubApp {

  start(args)

}
