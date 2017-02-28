# OpenVPN for Docker

OpenVPN server in a Docker container complete with an EasyRSA PKI CA.

## Quick Start
You can do this on a seperate machine and transfer $OVPN_DIR to the server or execute this direcly on the server.

configure
```
OVPN_DIR=/data/vpn
OVPN_DOMAIN=www.example.com
cd /my-dir
docker run -v $OVPN_DIR:/etc/openvpn --rm kperson/openvpn ovpn_genconfig -u udp://$OVPN_DOMAIN
#docker run -v $OVPN_DIR:/etc/openvpn --rm -e DNS_OVERRIDE=10.1.0.7 kperson/openvpn ovpn_genconfig -u udp://$OVPN_DOMAIN
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn ovpn_initpki
```

start the server
```
docker run -d --name vpn -v $OVPN_DIR:/etc/openvpn -p 1194:1194/udp --privileged kperson/openvpn
docker run -d --name vpn -v $OVPN_DIR:/etc/openvpn -v /ovpn_run:/usr/local/bin/ovpn_run -p 1194:1194/udp --privileged kperson/openvpn


#docker network connect --ip 10.1.0.5 vidicastnet vpn
#docker network connect --ip 10.2.0.5 vidicastnet_stage vpn
#docker network connect --ip 10.3.0.5 vidicastnet_dev vpn

#docker run -d --name dns --cap-add NET_ADMIN andyshinn/dnsmasq:2.76 -S 127.0.0.11 -R
#docker network connect --ip 10.1.0.7 vidicastnet dns
#docker network connect --ip 10.2.0.7 vidicastnet_stage dns
#docker network connect --ip 10.3.0.7 vidicastnet_dev dns
```

## Generating Client Credentials
On the server
```bash
OVPN_PROFILE=bob
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn easyrsa build-client-full $OVPN_PROFILE nopass
docker run -v $OVPN_DIR:/etc/openvpn --rm kperson/openvpn ovpn_getclient $OVPN_PROFILE > $OVPN_PROFILE.ovpn
```

On your local machine
```bash
scp root@$DOMAIN:/my-dir/bob.ovpn bob.ovpn
```

On the server
```bash
rm /my-dir/$OVPN_PROFILE.ovpn
```


## Using your Credentials

* Open  `<ovpn_profile>.ovpn` with [Tunnelblick](https://tunnelblick.net/) on your MAC.
* For other operating systems, see [HOWTO Connect Client Configuration](https://openvpn.net/index.php/access-server/docs/admin-guides-sp-859543150/howto-connect-client-configuration.html).