function validateBookForm() { 
    let title = document.getElementById("title").value; 
    let author = document.getElementById("author").value; 
    if (title === "" || author === "") { 
    alert("Both title and author must be filled out."); 
    return false; 
    } 
    return true; 
} 

function searchBooks() { 
    let query = document.getElementById("searchBox").value; 
    let xhttp = new XMLHttpRequest(); 
    xhttp.onreadystatechange = function () { 
    if (this.readyState == 4 && this.status == 200) { 
        document.getElementById("results").innerHTML = this.responseText; 
    } 
    }; 
    xhttp.open("GET", "ajax/liveSearch.jsp?q=" + query, true); 
    xhttp.send(); 
} 