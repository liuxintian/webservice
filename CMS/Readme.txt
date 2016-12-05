
//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Linode server info
Linda ssh: ssh root@45.118.135.83   [Please ask Jayant for password]
Linode path:/var/lib/omtwork/
				Tomcat-Certificate [Tomcat certificate - need to be updated]
				tomcat8-1
				tomcat8-2
				tomcat8-3
				tomcat8-4
				tomcat8-5
				Readme.txt   [This file]
// All sms code on GIThub


//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Tomcat Server list [Deployed on ROOT]:
tomcat8-1  http://45.118.135.83:15660/  https://45.118.135.83:15661/	[Tomcat:8015,8019] [SharePrice] 
tomcat8-2  http://45.118.135.83:8282/	[Tomcat:8025,8029] [cms-master-admin]
tomcat8-3  http://45.118.135.83:8383/ [Tomcat:8035,8039] [cms-mobile-api]
tomcat8-4  http://45.118.135.83:8484/ [Tomcat:8045,8049] [cms-cust-admin]
tomcat8-5  http://45.118.135.83:8585/ [Tomcat:8055,8059] [cms-cust-api]

//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Configuration [Need to be changed in the future when the domain name changed]:
// file path: cms-master-data com.omt.cms.master.data.config.postgress.properties
master.admin.cms.url=http://45.118.135.83:8282     [http://mobileapi.omnimarkettide.com/]
// file path: cms-cust-data com.omt.cms.cust.data.config
cms.instance.admin.url=http://45.118.135.83:8484

//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Restore certificate command [linux ubuntu] [still need to be updated as the mapping for IP ssl is not recognized]
$> openssl pkcs12 -export -in omt.crt -inkey certificate_key.key -out mycert.p12 -name tomcat -CAfile intermediate.crt -caname root -chain
$> keytool -importkeystore -srckeystore mycert.p12 -destkeystore tomcat.keystore
$> keytool -list -keystore tomcat.keystore

//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Code changed for [uploading file path url]:
1.	package com.omt.cms.cust.service.impl; DocumentLinksMediaServiceImpl2.	package com.omt.cms.master.service.impl; CompanyServiceImpl//— — — — — — — —— — — — — — — — — — — — — — — — — — — — — — — —
// Steps left:
1. Submit the linode server IP [45.118.135.83] to Morningstar and request for adding it into whitelist
2. Map the linode server IP [45.118.135.83] to Specific domain name;
3. Request new https certificate files for the linode server IP [45.118.135.83]

