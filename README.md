# Xmen-Meli
API that looking for mutants depending of their DNA nitrogen base , this DNA its a matriz nxn that have to have this patrons "AAAA", "TTTT", "CCCC", "GGGG" to consider a person like mutant. 

This API rest have two resources
- /mutant -> return if the person is or not mutant depend of matrix "AAAA", "TTTT", "CCCC", "GGGG". This matrix have to have more than one combination. 

Request: 
{
    "dna": [
        "AgGtGt",
        "CAGTGt",
        "TTcTGc",
        "AtcAGt",
        "CCaCTt",
        "CCaCTa"
    ]
}

response: 
{
    "is_mutant": true,
    "message": "mutant found successfully",
    "statusCode": "200"
}
OR
{
    "is_mutant": false,
    "message": "mutant not found",
    "statusCode": "403"
}
OR
{
    "is_mutant": false,
    "message": "the matrix is not square or DNA is misspelled ",
    "statusCode": "400"
}

## WAY TO USE

if you want to use in local you only need to uncomment lines in file application.properties that have comment (LOCAL) and if you want to use in cloud you have to uncomment preview lines and descomment lines with comment (AWS).
#### IMPORTANT don't change the lines with comment (NOT TOUCH)

## IF YOU USE AWS

### CRULS

#### POST (/mutant)
curl --location --request POST 'http://xmenmelielasticbeanstalk-env.eba-q4kdimsm.us-east-2.elasticbeanstalk.com/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": [
        "AgGtG",
        "CAGTG",
        "TTaTG",
        "AtccG",
        "CCcCg"
    ]
}'

#### GET (/stats)
curl --location --request GET 'http://xmenmelielasticbeanstalk-env.eba-q4kdimsm.us-east-2.elasticbeanstalk.com/stats'

## IF YOU USE LOCAL
### CURLS
#### POST (/mutant)
curl --location --request POST 'localhost:8080/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "dna": [
        "AgGtGt",
        "CAGTGt",
        "TTcTGc",
        "AtcAGt",
        "CCaCTa",
        "CCcCTa"
    ]
}'
#### GET (/stats)
curl --location --request GET 'localhost:8080/stats'


