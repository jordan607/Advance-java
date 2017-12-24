<%@page isELIgnored="false" %>
<h1 style="color:red;text-align:center">Student Regisration from</h1>

<form action="controller" method="post">
  <table border="0">
    
    <tr>
      <td>Student name:: </td>
      <td><input type="text" name="sname"></td>
    </tr>
    <tr>
      <td>Marks1:: </td>
      <td><input type="text" name="m1"></td>
    </tr>
    <tr>
      <td>Marks2:: </td>
      <td><input type="text" name="m2"></td>
    </tr>
    <tr>
      <td>Marks3:: </td>
      <td><input type="text" name="m3"></td>
    </tr>
    <tr>
       <td><input type="submit"  value="submit"></td>
       <td><input type="reset"  value="cancel"></td>
    </tr>
  </table>
    <input type="hidden"  name="cToken"  value="${sToken}">
</form>
 <br> <br>request count::  ${reqCount}