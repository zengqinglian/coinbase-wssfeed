# coinbase-wssfeed
coinbase-wssfeed
https://docs.cloud.coinbase.com/exchange/docs/channels

Assumption:
Print out price on each tick :  
Two possible way : 
- monitor top price in order book and print order book when top bid or ask changed. 
- Print out order book after received each ticker message.  The Code here is doing this. 



Connect to free coinbase wss feed.
- It is WSS (TCP) based protocol , so the code does not have recovery function in it. 
- Most of case TCP miss message due to hardware error or network congestion.  In this case, you can restart the feed. 
- Another reason i did not add recovery is all the products message subscribed are send from the same URL and port. 
Snapshot message is not send from another URL or port. 

Improvement: 
- add Replay function
- add Function to broadcast the message to downstream 

Jar file is also provided:  (Java 11)

To run from command line
java -cp GSR-Test-Qinglian-Zeng-1.0-SNAPSHOT-jar-with-dependencies.jar qinglian.zeng.coinbase.ws.feed.Main

Ctrl+C to stop. 



