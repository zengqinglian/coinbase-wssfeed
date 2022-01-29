# coinbase-wssfeed
coinbase-wssfeed
https://docs.cloud.coinbase.com/exchange/docs/channels

Connect to free coinbase wss feed.
It is WSS (TCP) based protocol , so the code does not have recovery function in it. 
once subscribed, the coin base feed will send the latest ASKs and BIDS snapshot . then the code will receive l2 level update.  When received ticker message, code print order book.

TODO:
Unit test is missing. 



