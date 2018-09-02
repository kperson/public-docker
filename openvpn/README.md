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
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn ovpn_initpki
```

start the server
```
docker run -d -v $OVPN_DIR:/etc/openvpn --privileged kperson/openvpn
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

## Revoking Client Credentials

### Enable revoke list (run only once)

```bash
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn easyrsa gen-crl
chown nobody:nogroup $OVPN_DIR/pki/crl.pem
```

### Revoke Credentials

```bash
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn easyrsa revoke $OVPN_PROFILE
docker run -v $OVPN_DIR:/etc/openvpn --rm -it kperson/openvpn easyrsa gen-crl
docker restart vpn
```

## Using your Credentials

* Open  `<ovpn_profile>.ovpn` with [Tunnelblick](https://tunnelblick.net/) on your MAC.
* For other operating systems, see [HOWTO Connect Client Configuration](https://openvpn.net/index.php/access-server/docs/admin-guides-sp-859543150/howto-connect-client-configuration.html).