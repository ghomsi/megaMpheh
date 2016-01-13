<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="initProcess.jsp">
	<p>
	<label for="nom">Entrez ici votre nom de famille
	:</label><br />
	<input type="text" name="nom" id="nom" tabindex="10" />
	</p>
	<p>
	<label for="prenom">Entrez ici votre prénom :</label><br />
	<input type="text" name="prenom" id="prenom" tabindex="20"
	/>
	</p>
	<p>
	<label for="pays">Dans quel(s) pays avez-vous déjà voyagé
	?</label><br />
	<select name="pays" id="pays" multiple="multiple"
	tabindex="30">
	<option value="France">France</option>
	<option value="Espagne">Espagne</option>
	<option value="Italie">Italie</option>
	<option value="Royaume-uni">Royaume-Uni</option>
	<option value="Canada">Canada</option>
	<option value="Etats-unis">Etats-Unis</option>
	<option value="Chine">Chine</option>
	<option value="Japon">Japon</option>
	</select>
	</p>
	<p>
	<label for="autre">Entrez ici les autres pays que vous avez
	visités, séparés par une virgule :</label><br />
	<textarea id="autre" name="autre" rows="2" cols="40"
	tabindex="40" placeholder="Ex: Norvège, Chili, Nouvelle-
	Zélande"></textarea>
	</p>
	<p>
	<input type="submit" value="Valider" /> <input type="reset"
	value="Remettre à zéro" />
	</p>
	</form>
</body>
</html>