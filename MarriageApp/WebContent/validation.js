 function validate(frm){
    alert("client side form validations..")
    //set hidden box(vflag) value to "yes" indicating the form validations are done
    frm.vflag.value="yes";
    //empty form validation Error messages
    document.getElementById("pnameErr").innerHTML="";
    document.getElementById("pageErr").innerHTML="";
    document.getElementById("genderErr").innerHTML="";
 
   //read form data
   var name=frm.pname.value;
   var age=frm.page.value;
   var gender=frm.gender.value;
   
   //Write client side form validation logics
   if(name==""){  //required
     document.getElementById("pnameErr").innerHTML="person name is mandatory";
     frm.pname.focus();
     return false;
   }
   
   if(age==""){  //required
     document.getElementById("pageErr").innerHTML="person age is mandatory";
     frm.page.focus();
     return false;
   }
   else if(isNaN(age)){
     document.getElementById("pageErr").innerHTML="person age must be numeric value";
     frm.page.focus();
     frm.page.value="";
     return false;
   }
   else if(age<1 || age>125){
     document.getElementById("pageErr").innerHTML="person age must be in the range of 1 through 125";
     frm.page.focus();
     frm.page.value="";
     return false;
   }
   
   if(gender==""){
       document.getElementById("genderErr").innerHTML="person Gender must be selected";
     frm.gender.focus();
     return false;
   }
   return true;
 }//function
