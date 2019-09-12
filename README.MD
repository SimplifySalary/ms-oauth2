<h1>Simplify Salary</h1>
<p>MS OAuth2</p>
<h5>Microsevicio de autorización y autenticación de clientes y aplicaciones.</h5>
<p>Esta implementación es en mysql para los clientes y usuarios, los tokens quedan alocados en redis</p> 
<h5>Rel:</h5>
<li><a href="https://spring.io/projects/spring-security-oauth">Spring OAuth2 Implementation</a></li>
<li><a href="https://oauth.net/2/">OAuth2 Protocol</a></li>

<h4>Dependencias</h4>
<p>Redis</p>
<p>Mysql: 
    <ul>creacion de schema: sql/create_schema.sql</ul>
    <ul>inserta clientes/usuarios/roles sql/example_inserts.sql</ul>
</p>

<p>En memoria hay un usuario/password (simple/simple) con el rol ADMIN_ROL</p>

<p>http://localhost:8080/swagger-ui.html</p>

<h4>Obtencion de token:</h4>

<p><h5>grant_type=password:</h5> 
    <ul>[POST] localhost:8080/oauth/token?grant_type=password&username=user&password=pass</ul>
    <ul>Authorization:Basic Y2xpZW50SWQxOnNlY3JldA==</ul>
</p>
    
    Example response:
    {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwidXNlcl9uYW1lIjoidXNlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0IiwiYXBpIl0sImV4cCI6MTU2NTczNzA3NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiI1OWNhNDRmZi01ZWFmLTQwMDQtYWMxYi00YTBjMWZjMDYzNzIiLCJjbGllbnRfaWQiOiJjbGllbnRJZDEifQ.Yw41o9ifMe4Zcminf3-pUQwkTHjd3atyc3M9btIBKY_ZS5RmQfBooI-t2xhsXet4gsAmxPNJkG9xt17xWpSUgBT581b0Fm4uBoK5S7RUhfYdS9q5lZIDnFPJvWDAwPmhuGEDh2CyrMfSSJwPQwPmWiiU5mB5tJyz-So1exOvSKWOtCcwbUd0G8TVR-qrnDAWLW8nNjl8MsDtzwKb3lhv7fTSuUJooEKNUmizlKuotz2uClKddHKXwYqjNCFNbAXgOpOeBoE5TJkPawUa-5duK77c3rAbc-DN9U9G8DQi4Nwdk4fdxGMa7nvgrGon-HOwx7xZcdUOyKFZRNIS2FeJ3Q",
        "token_type": "bearer",
        "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwidXNlcl9uYW1lIjoidXNlciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0IiwiYXBpIl0sImF0aSI6IjU5Y2E0NGZmLTVlYWYtNDAwNC1hYzFiLTRhMGMxZmMwNjM3MiIsImV4cCI6MTU2ODMyODc3NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiI0ZjMwMDljNC1lZWFkLTQyMDctOWY3Yi04Y2FhYjkwYTQwZGQiLCJjbGllbnRfaWQiOiJjbGllbnRJZDEifQ.EfggN96glaJmiwq8oBOA9aI9rkXS6lf631rh2vGmjo-lo-MEGjz8lQ2EgegLPDemtapkARJIf4NiT6t3r5BnrNZsuQE79TmORBWJJDnT5MxvKYGw6LzzSXcOoyKX6Rur6AMdW1Mg7EsgwKcirlvOQXNNv7NQU0iHuMQQJp2EIC-LDlK2LgG84keqEaOYOKowqJSGDKrtatgmY25GNpIY6lw_vm2YWWYZwyNdlZcxTQ8shiyjA7pXWKkhqG4kme5aW0AAFixf977VfZXmZf_ADBhtHLwT2dqXczQ3R7n75Kwc-Ci0fCzbiV2V2U-JHrDhB1Q4lcj3Ck4IAz6VRDYJVg",
        "expires_in": 299,
        "scope": "read write trust api",
        "jti": "59ca44ff-5eaf-4004-ac1b-4a0c1fc06372"
    }
   
<p><h5>grant_type=client_credentials: </h5>
    <ul>[POST] localhost:8080/oauth/token?grant_type=client_credentials</ul>
    <ul>Authorization:Basic Y2xpZW50SWQxOnNlY3JldA==</ul>
</p>    
<ul>[POST] clientId:secret@localhost:8080/oauth/token?grant_type=password&username=user&password=pass</ul>

    Example response:
    {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiLCJhcGkiXSwiZXhwIjoxNTY1NzM3NDA0LCJhdXRob3JpdGllcyI6WyJST0xFX0NMSUVOVCJdLCJqdGkiOiI5Zjg1YTNhMi1jMmU3LTRmNjktOWQ4NS1hNWFmMTgyYmE4M2IiLCJjbGllbnRfaWQiOiJjbGllbnRJZDEifQ.q004gPb9ZyhuwbYujD8P-lIkkF9juFtcNFlNqZbrwh3W71GhXio6q2Y_3ive3b92h_ZwzRbbbzbrDrh3QQ_D_CapSP2h4nrddU8ufONLV0zzpA7HeO0r8FrHxica1i5cSuHz-aZcP_j_AGuPpI_1g_DUEY-mSjHBnEeKgnwUmcIdGBPwq8hJ1XpsB1n7PIv9cMT43xgNHEytDJ7l59ZFn3ZxAij4NnHsJ-Qt_p3cnQv7BNeka86zyJ2qUebf8SWhb3A0EKEL4UNZgQVqx1rP6ovaUd7KUH6D3R4yFWTAnR1ZSbh1a5hHJnz9BhboJ2254DVWvrt_7TioNNktzt9NEw",
        "token_type": "bearer",
        "expires_in": 299,
        "scope": "read write trust api",
        "jti": "9f85a3a2-c2e7-4f69-9d85-a5af182ba83b"
    }
    
    Para hacer uso del provider web, quien valida token en AS400, usar el header:
    Agregando el header: channel:web

<p><h5>response_type=code: </h5>
    <p>Code generation: 
    <ul>[GET] http://localhost:8080/oauth/authorize?client_id=clientId1&response_type=code</ul>
    </p>
    <p>Login: Username: user Password: user</p>
    <p>Token generation: 
        <ul>[POST] localhost:8080/oauth/token?grant_type=authorization_code&code={code}&client_id=clientId1</ul>
        <ul> Authorization: Basic Y2xpZW50OnNlY3JldA==</ul>
    </p>
</p> 

    Example response:
    {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiLCJhcGkiXSwiZXhwIjoxNTY1NzM3NDA0LCJhdXRob3JpdGllcyI6WyJST0xFX0NMSUVOVCJdLCJqdGkiOiI5Zjg1YTNhMi1jMmU3LTRmNjktOWQ4NS1hNWFmMTgyYmE4M2IiLCJjbGllbnRfaWQiOiJjbGllbnRJZDEifQ.q004gPb9ZyhuwbYujD8P-lIkkF9juFtcNFlNqZbrwh3W71GhXio6q2Y_3ive3b92h_ZwzRbbbzbrDrh3QQ_D_CapSP2h4nrddU8ufONLV0zzpA7HeO0r8FrHxica1i5cSuHz-aZcP_j_AGuPpI_1g_DUEY-mSjHBnEeKgnwUmcIdGBPwq8hJ1XpsB1n7PIv9cMT43xgNHEytDJ7l59ZFn3ZxAij4NnHsJ-Qt_p3cnQv7BNeka86zyJ2qUebf8SWhb3A0EKEL4UNZgQVqx1rP6ovaUd7KUH6D3R4yFWTAnR1ZSbh1a5hHJnz9BhboJ2254DVWvrt_7TioNNktzt9NEw",
        "token_type": "bearer",
        "expires_in": 299,
        "scope": "read write trust api",
        "jti": "9f85a3a2-c2e7-4f69-9d85-a5af182ba83b"
    }
        
    Para hacer uso del provider web, quien valida token en AS400, usar el header:
    Agregando el header: channel:web

<p><h5>response_type=token: </h5>
    <p>Code generation: 
    <ul>[GET] http://localhost:8080/oauth/authorize?client_id=clientId1&response_type=token</ul>
    </p>
    <p>Login: Username: user Password: user</p>
</p>

<h4>Asymmetric KeyPair</h4>

Cada ms que utilice este oauth2 server debera tener la public key (del keystore de oauth2).
Como generarla:
https://www.baeldung.com/spring-security-oauth-jwt