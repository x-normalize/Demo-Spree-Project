#!/bin/bash

# Get the current directory
Postman=$(pwd)

# Change to the Postman directory
cd "$Postman"

echo "Spree_collections"

# Set the collection and environment file paths
postman_collection="Spree_collection.json"
postman_environment="Spree_environment.json"

# Run Newman with the specified collection and environment
newman run "$Spree_collection" -e "$Spree_environment" -r htmlextra

# Pause to see the output
read -p "Press Enter to continue...
