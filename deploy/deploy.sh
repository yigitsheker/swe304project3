#!/bin/bash

echo "Starting deployment..."

cd /home/ubuntu/swe304project3-deploy || exit

echo "Pulling latest Docker image..."
docker compose pull

echo "Stopping old container..."
docker compose down

echo "Starting new container..."
docker compose up -d

echo "Cleaning unused Docker images..."
docker image prune -f

echo "Deployment completed successfully."
