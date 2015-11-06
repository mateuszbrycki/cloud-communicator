<h1>index</h1>
<a href="facebook/index.html">Protected url by Facebook: facebook/index.html</a><br />
<a href="facebook/notprotected.html">Not protected page: facebook/notprotected.html</a><br />
<a href="facebookadmin/index.html">Protected url by Facebook with ROLE_ADMIN: facebookadmin/index.html</a><br />
<a href="facebookcustom/index.html">Protected url by Facebook with custom authorizer (= must be a <em>HttpProfile</em> where the username starts with "jle"): facebookcustom/index.html</a><br />
<a href="twitter/index.html">Protected url by Twitter: twitter/index.html</a> or <a href="twitter/index.html?client_name=FacebookClient">by Facebook: twitter/index.html?client_name=FacebookClient</a><br />
<a href="form/index.html">Protected url by form authentication: form/index.html</a><br />
<a href="basicauth/index.html">Protected url by indirect basic auth: basicauth/index.html</a><br />
<a href="cas/index.html">Protected url by CAS: cas/index.html</a><br />
<a href="saml/index.html">Protected url by SAML2: saml/index.html</a><br />
<a href="oidc/index.html">Protected url by OpenID Connect: oidc/index.html</a><br />
<a href="protected/index.html">Protected url: protected/index.html</a><br />
<br />
<a href="jwt.html">Generate a JWT token</a><br />
<a href="dba/index.html">Protected url by DirectBasicAuthClient (POST the <em>Authorization</em> header with value: <em>Basic amxlbGV1OmpsZWxldQ==</em>) then by ParameterClient (with request parameter: token=<em>jwt_generated_token</em>): /dba/index.html</a><br />
<a href="rest-jwt/index.html">Protected url by ParameterClient (with JwtAuthenticator): /rest-jwt/index.html?token=<em>jwt_generated_token</em></a><br />
<br />
<a href="logout?url=https://localhost:8443/cas/logout">logout</a>
<br /><br />
profile : ${profile}
<br /><br />
<hr />

<a href="${urlFacebook}">Authenticate with Facebook</a><br />
<a href="${urlTwitter}">Authenticate with Twitter</a><br />
<a href="${urlForm}">Authenticate with form</a><br />
<a href="${urlBasicAuth}">Authenticate with basic auth</a><br />
<a href="${urlCas}">Authenticate with CAS</a><br />
<a href="${urlSaml}">Authenticate with SAML</a><br />
<a href="${urlOidc}">Authenticate with OpenID Connect</a><br />
