#!/bin/bash

# Use the first argument as the number of requests, defaulting to 50 if not provided
num_requests=${1:-60}

# Loop specified number of times to send POST requests asynchronously
for i in $(seq 1 "$num_requests")
do
  echo "Request #$i"
  curl "http://localhost:8080/api/redis/dequeue?requestNumber=$i" &
done

# Wait for all background jobs to complete
wait

echo "All requests sent."
