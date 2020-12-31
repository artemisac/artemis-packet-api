# Artemis Packet API
[![](https://jitpack.io/v/artemisac/Artemis-Packet-API.svg)](https://jitpack.io/#artemisac/Artemis-Packet-API)

## Changelog
### 30/12/2020
- Recoded injection. Now works on the basis of directly overriding the server bootstrap
inside of NMS. This resembles ViaVersion's solution and works pretty much flawlessly. This
transition gives us the benefit to handle connections ***before the login.***
- Added Junit tests, Javadocs and other cool stuff any maven project should have. I'm
not quite sure whether Jitpack will directly allow access for these. I cross my fingers.
If not, I'll attempt to get this provided on Maven Central.
- Reworked a lot of the EnumProtocol system. I've determined that ViaVersion is just not
reliable enough to use to translate protocols. Henceforth, I will not be translating
them at all. I'll unfortunately have to provide an enum protocol for every major. It is
not tedious work thanks to the https://wiki.vg changelog, it's nonetheless not how I
wished for this to be handled. Perhaps I'll make a tool that automates the process.
- Fixed UseEntity protocol not properly working.

## License
All rights reserved - Ghast Holdings LLC

Any use of this code for non-educational and testing purposes is stricly prohibited. 
For commercial licenses of this code, contact us on discord (Ghast#0001)
