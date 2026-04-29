#!/bin/bash

echo "Starting deployment..."

cd /home/ubuntu/swe304project3-deploy || exit 1

echo "Pulling latest Docker image..."
docker compose pull

echo "Stopping old compose services..."
docker compose down || true

echo "Removing old container if exists..."
docker rm -f swe304project3-container || true

echo "Starting new container with latest image..."
docker compose up -d --force-recreate

echo "Cleaning unused Docker images..."
docker image prune -f

echo "Deployment completed successfully."