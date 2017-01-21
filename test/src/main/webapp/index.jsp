<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
		function validate_form(obj) {
			var valid = true;
			var pattern1 = /^[а-яі-ї]{1,20}$/i;
			var email_pattern = /^[0-9a-z_]+@[0-9a-z_]+\.[a-z]{2,5}$/i;
			var number_pattern = /^\d{5,12}$/;
			var date_pattern = /^\d{4}-\d{2}-\d{2}$/;
			
			if (!pattern1.test(obj.firstName.value)) {
				valid = false;
				alert("Ім'я повинно містити тільки літери кирилицею");
			}
			if (!pattern1.test(obj.lastName.value)) {
				valid = false;
				alert("Прізвище повинно містити тільки літери кирилицею");
			}
			if (!pattern1.test(obj.country.value)) {
				valid = false;
				alert("Некоректна назва країни");
			}
			if (!pattern1.test(obj.city.value)) {
				valid = false;
				alert("Некоректна назва міста");
			}
			if (!date_pattern.test(obj.birthday.value)) {
				valid = false;
				alert("Дата введена некорректно. Формат РРРР-ММ-ДД");
			}
			if (!number_pattern.test(obj.number.value)) {
				valid = false;
				alert("Номер телефону повинен містити тільки цифри в кількості від 5 до 12");
			}
			if (!email_pattern.test(obj.email.value)) {
				valid = false;
				alert("Некоректний формат електронної пошти");
			}
			return valid;
		}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Анкета</title>
</head>
<body>
	
	<form name="contact_form" action="save" method="post" onsubmit="return validate_form(contact_form);">
		<table align="center">
			<caption>Заповніть всі поля</caption>
			<tr>
				<td align="right"><label for="firstName">Ім'я:</label></td>
				<td><input type="text" name="firstName" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="lastName">Прізвище:</label></td>
				<td><input type="text" name="lastName" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="birthday">Дата народження:</label></td>
				<td><input type="text" name="birthday" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="number">Номер телефону:</label></td>
				<td><input type="text" name="number" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="email">Електронна пошта:</label></td>
				<td><input type="text" name="email" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="country">Країна:</label></td>
				<td><input type="text" name="country" size="50" /></td>
			</tr>
			<tr>
				<td align="right"><label for="city">Місто:</label></td>
				<td><input type="text" name="city" size="50" /></td>
				<td><select name="district">
				 <option value="(Вінницька)">Вінницька</option>
				 <option value="(Волинська)">Волинська</option>
				 <option value="(Дніпропетровська)">Дніпропетровська</option>
				 <option value="(Донецька)">Донецька</option>
				 <option value="(Житомирська)">Житомирська</option>
				 <option value="(Закарпатська)">Закарпатська</option>
				 <option value="(Запоріжська)">Запоріжська</option>
				 <option value="(Івано-Франківська)">Івано-Франківська</option>
				 <option value="(Київська)">Київська</option>
				 <option value="(Кіровоградська)">Кіровоградська</option>
				 <option value="(Крим)">Крим</option>
				 <option value="(Луганська)">Луганська</option>
				 <option value="(Львівська)">Львівська</option>
				 <option value="(Миколаївська)">Миколаївська</option>
				 <option value="(Одеська)">Одеська</option>
				 <option value="(Полтавська)">Полтавська</option>
				 <option value="(Рівненська)">Рівненська</option>
				 <option value="(Сумська)">Сумська</option>
				 <option value="(Тернопільська)">Тернопільська</option>
				 <option value="(Харківська)">Харківська</option>
				 <option value="(Херсонська)">Херсонська</option>
				 <option value="(Хмельницька)">Хмельницька</option>
				 <option value="(Черкаська)">Черкаська</option>
				 <option value="(Чернігівська)">Чернігівська</option>
				 <option value="(Черновицька)">Черновицька</option>
				</select></td>
			</tr>
		</table>
		<p align="center">
			<input type="submit" value="OK" />
			<input type="reset" value="Очистити" />
		</p>
		<p align="center" style="color: green">${success}</p>
		<p align="center" style="color: red">${error}</p>
	</form>
	<p><a href="contactsList.jsp">Показати всі контакти</a> </p>
</body>
</html>