# OpenMaple #

OpenMaple is a fast [MapleStory][ms] emulator aiming to bring openness back to 
a community near wrecked by solely proprietary innovations. We're not going to 
do it with [overbearing licenses][agpl] like previous emulators. We're building 
a new emulator from scratch leveraging modern technologies to bring 
[MapleStory][ms] emulation into the modern age. We're employing modern design 
decisions to increase maintainability, scalability, and customizability. Best 
of all, we're letting OpenMaple go free in every sense of the word. It's free 
from cost, free from restriction, and free from worry. OpenMaple just is.

### The Technologies ###

OpenMaple, like many existing [MapleStory][ms] emulators, is in Java, but now 
all of the code is being written using idioms introduced with [Java 7][java7] 
and will be updated where appropriate to utilize newer features as they arise. 
OpenMaple is built using [Apache's Maven][mvn] build system. It's fast, sleek, 
easy to use, and it handles all our dependencies elegantly. As for those 
dependencies, we're using [Netty][netty] and [Hibernate][hib] to ensure that 
the emulator is fast, efficient, and load-bearing. Additionally, we use 
Google's [Guava][guava] library to provide a set of common libraries to use 
throughout the emulator where needed. We want our emulator to be able to get 
the most that it can out of the hardware, and we're sure that users want that 
too. We also use [SLF4J][slf4j] to support our logging to provide a flexible, 
easy-to-use logging system. OpenEmu aims to give choice back to the end-user.

### The Design ###

Unlike existing emulators, OpenMaple follows modern design idioms that have 
been tried and tested in production Java software. The entire system is based 
around an event model using [Guava's][guava] EventBus and custom packet 
serializers and deserializers. This makes it easier for the emulator to be 
ported to different versions of [MapleStory][ms] with minimal effort while 
maintaining the same basic framework. We use [Hibernate][hib] with plain-old 
Java objects to make working with a database simpler and more flexible. 
Currently, OpenMaple is being built for the official v.40 version. Once that's 
more feature complete, we'll move on to emulating newer versions.

### Contributing ###

If you're as excited about this as we are, we totally understand that you want 
to get involved and we want you to get involved. Make sure to read up on the 
guidelines for contributing [online][contrib] or in CONTRIBUTING.md. You'll 
need to follow the instructions therein to get your code upstream. Once you're 
ready to go, just fork and start hacking away. If you need help on figuring out 
what needs to be done or even how to do it, you'll be able to find aaronweiss74 
and Fraysa on #vana on the [FyreChat IRC Network][fyrechat]. We appreciate your 
support and look forward to your contributions!

[ms]:       <http://www.nexon.net/landing/maplestory/>      "MapleStory"
[agpl]:     <https://www.gnu.org/licenses/agpl-3.0.html>    "Affero GPL"
[java7]:    <http://cl.ly/QRsf>                             "Java 7"
[mvn]:      <https://maven.apache.org/>                     "Apache Maven"
[netty]:    <http://netty.io/>                              "Netty"
[hib]:      <http://www.hibernate.org/>                     "Hibernate"
[guava]:    <https://code.google.com/p/guava-libraries/>    "Google Guava"
[slf4j]:    <http://slf4j.org/>                             "SLF4J"
[contrib]:  <http://cl.ly/QRXw>                             "CONTRIBUTING.md"
[fyrechat]: <http://www.fyrechat.net/>                      "Fyrechat IRC"