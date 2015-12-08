{
    "user":"${login}",    
    "pet": {
            "nickname":"${nickname}",
            "speciesName":"${speciesName}",
            "birthdate":"${birthdate}",
            "male": <#if male == true>"male"<#else>"female"</#if>
    },
    "result":"${result}"
}