# miguelfonseca-200421

## Ambiente

- javac 21.0.11
- Apache Maven 3.8.7

## Musica Lib


#Listas
```
curl --request GET \
--url http://localhost:7100/musiclist \
--header 'authorization: Bearer user1'
```

# Musicas das Listas
curl --request GET \
--url http://localhost:7100/music/1/items \
--header 'authorization: Bearer user1'