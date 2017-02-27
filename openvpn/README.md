# OpenVPN for Docker

OpenVPN server in a Docker container complete with an EasyRSA PKI CA.

## Quick Start

* Run `./init-vpn` on the server.  Only do this ONCE. Follow the prompts.
* When running `./start-prod` the VPN will start.

# Generating Client Credentials

* Run  `./genclient-vpn <your_name>`.
* Use SCP to copy <your_name>.ovpn to your develomment machine ( development machine: (`scp root@www.vidicast.me:/root/live-video/server/<your_name>.ovpn <your_name>.ovpn`).
* Remove <your_name>.ovpn from the server (server: `rm <your_name>.ovpn`).

# Using your Credentials

* Open  <your_name>.ovpn with [Tunnelblick](https://tunnelblick.net/) on your MAC.
* For other operating systems, see [HOWTO Connect Client Configuration](https://openvpn.net/index.php/access-server/docs/admin-guides-sp-859543150/howto-connect-client-configuration.html).