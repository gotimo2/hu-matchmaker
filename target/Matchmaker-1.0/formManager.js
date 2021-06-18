
org_form = document.getElementById("org-form")
console.log(org_form)
    org_form.onsubmit = async (a) => {
        a.preventDefault();
        let fd = new FormData(org_form);
        let ed = new URLSearchParams(fd.entries())
        fetch("/match/newmatch", {
            method: 'POST',
            body: ed,
        })
            .then(response => {
                if (response.ok){
                    response.json()
                        .then(data => console.log(data))
                }
                else {
                    response.json()
                        .then(data => console.log(data))
                }
            })
            .catch((error) => {
                window.alert("there was an error: " + error);
            })
    };
