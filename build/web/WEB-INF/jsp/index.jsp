<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <h1>Smoke test for admin user</h1>
        <p>Hello! This is the default welcome page for Pet project.</p>
        <a href="http://localhost:8080/PetWebApp/PetServlet">Pet Login</a>
        <h1>Login</h1>
        <form action="LoginServlet" method="get">
            <table>
                <tr>
                    <td>User</td>
			<td><input name="login" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input name="password" type="password" /></td>
		</tr>
		</table>
		<input type="submit" />
	</form>
        <h1>Validation</h1>
        <form action="ValidationServlet" method="get">
            <table>
                <tr>
                    <td>Login</td>
                    <td><input name="login" /></td>
		</tr>
            </table>
            <input type="submit" />
	</form>
        <h1>Register</h1>
        <form action="RegisterServlet" method="get">
            <table>
                <tr>
                    <td>User</td>
                    <td><input name="login" /></td>
		</tr>
		<tr>
                    <td>Password</td>
                    <td><input name="password" type="password"/></td>
		</tr>
		</table>
		<input type="submit" />
	</form>        
        <h1>Add Pet</h1>
        <form action="AddPetServlet" method="get">
            <table>
                <tr>
                    <td>Owner</td>
			<td><input name="login" /></td>
		</tr>
		<tr>
                    <td>Nickname</td>
                    <td><input name="nickname" /></td>
		</tr>
                <tr>
                    <td>Species Name</td>
                    <td><input name="speciesName" /></td>
		</tr>
                <tr>
                    <td>Birthdate</td>
                    <td><input name="birthdate" type="datetime"/></td>
		</tr>
                <tr>
                    <td>Male</td>
                    <td>
                    <input type="checkbox" name="male" />
                    </td>
		</tr>
		</table>
		<input type="submit" />
                <p>Tokens switched off for test purposes</p>
	</form>        
        <h1>Add Species</h1>
        <form action="AddSpeciesServlet" method="get">
            <table>
                <tr>
                    <td>Species</td>
                    <td><input name="s" /></td>
		</tr>
            </table>
            <input type="submit" />
	</form>

    </body>
</html>
