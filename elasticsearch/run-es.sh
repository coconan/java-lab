docker run -d --rm --name es -p 9200:9200 -e "discovery.type=single-node" elasticsearch:latest
