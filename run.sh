browsermob-proxy/bin/browsermob-proxy >proxy.out -port 9000 &
tail -f proxy.out | while read LOGLINE
do
   [[ "${LOGLINE}" == *"Started SelectChannelConnector"* ]] && pkill -P $$ tail
done
