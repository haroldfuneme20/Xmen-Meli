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
226 / 5000
Resultados de traducción
si quieres usar en local solo necesitas descomentar las líneas en el archivo application.properties que tienen comentario (LOCAL) y si quieres usar en la nube tienes que descomentar las líneas de vista previa y descommentar las líneas con comentario (AWS). 
## IMPORTANT don't change the lines with comment (NOT TOUCH)





