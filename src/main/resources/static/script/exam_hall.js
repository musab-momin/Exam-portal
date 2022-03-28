let toaster;
let toasterMssg;
window.onload = ()=>
{
    toaster = document.querySelector(".toaster");
    toasterMssg = document.getElementById("toaster-mssg");
    document.onkeydown = function (e) {
        return false;
    }
    test();
}

const test = ()=>
{
    window.open('/student/paper/','popUpWindow','fullscreen=yes, resizable=no,scrollbars=yes, toolbar=no, menubar=no, location=no, directories=no, status=no');
}

       


const submit_all = ()=>
            {
                for(let i=0; i<document.forms.length; i++)
                {
                    let form_data = new FormData(document.forms[i]);
                    console.log(document.forms[i]);
                    console.log(form_data);
                    fetch('/student/savepaper', {
                        method: 'POST',
                        body: form_data
                    }).then((response)=>{
                        return response.text();
                    }).then((data)=>{
                        console.log(data);
                        let executed = false;
                        if(data=='success')
                        {
                            if(!toaster.classList.contains("success")){
                                toasterMssg.innerHTML = "Paper Submitted Successfully!";
                                toaster.classList.remove("danger");
                                toaster.classList.add("success");
                            }
                        }
                        else
                        {
                            if(!toaster.classList.contains("danger")){
                                toasterMssg.innerHTML = "You Already Given This Paper";
                                toaster.classList.remove("success");
                                toaster.classList.add("danger");
                            }
                        }
                         const timeOut = setTimeout(()=>{
                            window.location.assign('/student/home');
                           }, 3000);
                        clearTimeOut(timeOut);
                    });
                }
            };

