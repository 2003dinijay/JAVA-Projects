function loadInternships() {
    const xhr = new XMLHttpRequest();

    xhr.open('GET', 'ajax/internshipList.jsp', true);

    xhr.send();

    xhr.onload = function() {
        if (xhr.status === 200) {
            document.getElementById('internshipDropdown').innerHTML = xhr.responseText;
        } else {
            showNotification('Error loading internships: ' + xhr.statusText, 'error');
        }
    };

    xhr.onerror = function() {
        showNotification('Network error occurred while loading internships', 'error');
    };
}

function validateRegistration() {
    let name = document.getElementById('name').value.trim();
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value;
    let isValid = true;
    let errorMessages = [];

    if (name === '') {
        errorMessages.push('Name is required');
        isValid = false;
    } else if (name.length < 3) {
        errorMessages.push('Name must be at least 3 characters');
        isValid = false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email === '') {
        errorMessages.push('Email is required');
        isValid = false;
    } else if (!emailRegex.test(email)) {
        errorMessages.push('Please enter a valid email address');
        isValid = false;
    }

    if (password === '') {
        errorMessages.push('Password is required');
        isValid = false;
    } else if (password.length < 6) {
        errorMessages.push('Password must be at least 6 characters');
        isValid = false;
    }

    if (password !== confirmPassword) {
        errorMessages.push('Passwords do not match');
        isValid = false;
    }

    if (!isValid) {
        showNotification(errorMessages.join('<br>'), 'error');
    }

    return isValid;
}

function validateLogin() {
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;
    let isValid = true;
    let errorMessages = [];

    if (email === '') {
        errorMessages.push('Email is required');
        isValid = false;
    }

    if (password === '') {
        errorMessages.push('Password is required');
        isValid = false;
    }

    if (!isValid) {
        showNotification(errorMessages.join('<br>'), 'error');
    }

    return isValid;
}


function validateInternshipPost() {
    let title = document.getElementById('title').value.trim();
    let description = document.getElementById('description').value.trim();
    let deadline = document.getElementById('deadline').value;
    let isValid = true;
    let errorMessages = [];

    if (title === '') {
        errorMessages.push('Title is required');
        isValid = false;
    }

    if (description === '') {
        errorMessages.push('Description is required');
        isValid = false;
    } else if (description.length < 50) {
        errorMessages.push('Description should be at least 50 characters');
        isValid = false;
    }

    if (deadline === '') {
        errorMessages.push('Deadline is required');
        isValid = false;
    } else {
        const currentDate = new Date();
        const selectedDate = new Date(deadline);

        if (selectedDate <= currentDate) {
            errorMessages.push('Deadline must be a future date');
            isValid = false;
        }
    }

    if (!isValid) {
        showNotification(errorMessages.join('<br>'), 'error');
    }

    return isValid;
}

function validateApplication() {
    let internshipId = document.getElementById('internshipId').value.trim();
    let coverLetter = document.getElementById('coverLetter').value.trim();
    let isValid = true;
    let errorMessages = [];

    if (internshipId === '' || internshipId === null) {
        errorMessages.push('Please select an internship');
        isValid = false;
    }

    if (coverLetter.length < 50) {
        errorMessages.push('Cover letter is recommended (at least 50 characters)');
    }

    if (!isValid) {
        showNotification(errorMessages.join('<br>'), 'error');
    } else if (errorMessages.length > 0) {
        showNotification(errorMessages.join('<br>'), 'warning');
    }

    return isValid;
}

function showNotification(message, type = 'info') {
    const notification = document.getElementById('notification');
    if (notification) {
        notification.className = 'notification ' + type;
        notification.innerHTML = message;
        notification.style.display = 'block';

        setTimeout(function() {
            notification.style.display = 'none';
        }, 5000);
    } else {
        alert(message);
    }
}

function clearForm(formId) {
    document.getElementById(formId).reset();
}

function confirmAction(message) {
    return confirm(message);
}