window.onload = ()=>
{
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
                            alert('Submitted successfully.....');
                            window.location.assign('/student/home');
                        }
                        else
                        {
                            alert('you already given this exam....!');
                            window.location.assign('/student/home');
                        }
                    });
                }
            };

