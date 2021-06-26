// const toggleMenu = () => {
//     let toggle = document.querySelector('.toggle');
//     let navigation = document.querySelector('.navigation');
//     let main = document.querySelector('.main');
//     toggle.classList.toggle('active');
//     navigation.classList.toggle('active');
//     main.classList.toggle('active');
// }
const addquestion = ()=>{
    // alert("this is working");
    let url = window.location.href;
    let pid = url.split('/');
    let n = pid[pid.length-1];
    n = (+n);

    let question_form = document.createElement('FORM');
    question_form.setAttribute('method', 'POST');
    question_form.setAttribute('action', '/Teacher/processQuestion');
    let question_div = document.createElement('DIV');
    question_div.classList.add('input-box')


    
    let paperId_field = document.createElement('INPUT');
    paperId_field.setAttribute('type', 'text');
    paperId_field.setAttribute('required', 'required');
    paperId_field.setAttribute('name', 'currentPaperId');
    paperId_field.setAttribute('value', n);
    paperId_field.setAttribute('hidden', 'true');
    

    let question_field = document.createElement('INPUT');
    question_field.setAttribute('type', 'text');
    question_field.setAttribute('required', 'required');
    question_field.setAttribute('placeholder', 'Question ');
    question_field.setAttribute('name', 'qName');
    
    question_div.appendChild(question_field);
    // question_form.appendChild(question_div);


    let option1_div = document.createElement('DIV');
    option1_div.classList.add('input-box')
    let option1_field = document.createElement('INPUT');
    option1_field.setAttribute('type', 'text');
    option1_field.setAttribute('required', 'required');
    option1_field.setAttribute('placeholder', 'option 1');
    option1_field.setAttribute('name', 'option1');
    option1_div.appendChild(option1_field);


    let option2_div = document.createElement('DIV');
    option2_div.classList.add('input-box')
    let option2_field = document.createElement('INPUT');
    option2_field.setAttribute('type', 'text');
    option2_field.setAttribute('required', 'required');
    option2_field.setAttribute('placeholder', 'option 2');
    option2_field.setAttribute('name', 'option2');
    option2_div.appendChild(option2_field);


    let option3_div = document.createElement('DIV');
    option3_div.classList.add('input-box')
    let option3_field = document.createElement('INPUT');
    option3_field.setAttribute('type', 'text');
    option3_field.setAttribute('required', 'required');
    option3_field.setAttribute('placeholder', 'option 3');
    option3_field.setAttribute('name', 'option3');
    option3_div.appendChild(option3_field);


    let option4_div = document.createElement('DIV');
    option4_div.classList.add('input-box')
    let option4_field = document.createElement('INPUT');
    option4_field.setAttribute('type', 'text');
    option4_field.setAttribute('required', 'required');
    option4_field.setAttribute('placeholder', 'option 4');
    option4_field.setAttribute('name', 'option4');
    option4_div.appendChild(option4_field);


    let optionC_div = document.createElement('DIV');
    optionC_div.classList.add('input-box')
    let optionC_field = document.createElement('INPUT');
    optionC_field.setAttribute('type', 'text');
    optionC_field.setAttribute('required', 'required');
    optionC_field.setAttribute('placeholder', 'correct option');
    optionC_field.setAttribute('name', 'optionC');
    optionC_div.appendChild(optionC_field);


    

    question_form.appendChild(paperId_field);
    question_form.appendChild(question_div);
    question_form.appendChild(option1_div);
    question_form.appendChild(option2_div);
    question_form.appendChild(option3_div);
    question_form.appendChild(option4_div);
    question_form.appendChild(optionC_div);
   

    let container = document.querySelector('.container');
    //container.appendChild(question_form);
    container.appendChild(question_form);
    // console.log(question_form);
    // console.log(question_div);
    // console.log(question_field);
    // console.log(question_div);

}

let num  = 2;

const numberCounter = ()=>
{
    let h2 = document.querySelector('.counter');
    h2.innerText = `Total Question: ${num}`;
    num = num + 1;
}


const submit_all = ()=>
{
   console.log('this is working');
   let bt = document.getElementById('validator');
    bt.validity.valid;
    for(let i=0; i<document.forms.length; i++)
    {
        let form_data = new FormData(document.forms[i]);
       console.log(document.forms[i]);
       console.log(form_data);
       fetch('/Teacher/processQuestion', {
            method: 'POST',
            body: form_data
        }).then((response)=>{
            return response.text();
        }).then((data)=>{
            console.log(data);
            let num = 1;
            if(num == 1 && data=='success')
            {
                console.log(num);
                alert('Questions are added successfully');
                window.location.assign('/Teacher/home');
                num++;
            }
            else if(data=='error' && num==1)
            {
                alert('something went wrong....');
                window.location.assign('/Teacher/home');
                num++;
            }
        });

    }
   
}