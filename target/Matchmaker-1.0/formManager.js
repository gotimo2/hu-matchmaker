
org_form = document.getElementById("org-form")
console.log(org_form)
    org_form.onsubmit = async (a) => {
        let fd = new FormData(org_form);
        console.log(fd.values())
        a.preventDefault();
        let plainFormData = Object.fromEntries(fd.entries())
        let fdJsonString = JSON.stringify(plainFormData)
        console.log(fdJsonString)
        console.log(plainFormData)
        console.log(fd)

        let response = await fetch("/match/newmatch", {
            method: 'POST',
            body: fdJsonString,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });



        let result = await response.json();

        console.log(result)
    };
