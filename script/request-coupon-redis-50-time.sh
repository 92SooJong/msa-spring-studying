#!/bin/bash

# URL of the endpoint
url="http://localhost:8080/api/redis/increment-coupon"

# Loop 50 times to send POST requests asynchronously
for i in {1..50}
do
  echo "Request #$i"
  curl -X POST "$url" &
done

# Wait for all background jobs to complete
wait

echo "All requests sent."
