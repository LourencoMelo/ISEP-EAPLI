#!/bin/bash
STOREPASS="forgotten"
for ENT in serverAgvManager client1AGV client2AGV client3AGV client4AGV client5AGV client6AGV client7AGV client8AGV client9AGV client10AGV client11AGV client12AGV client13AGV client14AGV client15AGV client16AGV; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (Every client trusts server_J)
for ENT in client1AGV client2AGV client3AGV client4AGV client5AGV client6AGV client7AGV client8AGV client9AGV client10AGV client11AGV client12AGV client13AGV client14AGV client15AGV client16AGV; do
 echo "yes"|keytool -import -alias ${ENT} -keystore serverAgvManager.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias serverAgvManager -keystore ${ENT}.jks -file serverAgvManager.pem -storepass ${STOREPASS}
done

keytool -list -keystore serverAgvManager.jks -storepass ${STOREPASS}
#######