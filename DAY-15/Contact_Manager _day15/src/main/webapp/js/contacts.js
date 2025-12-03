const API_URL = "http://localhost:8181/Contact_Manager_day15/contacts";




const addContactBtn = document.getElementById('addContactBtn');
const contactFormContainer = document.getElementById('contactFormContainer');
const contactForm = document.getElementById('contactForm');
const cancelBtn = document.getElementById('cancelBtn');
const contactsList = document.getElementById('contactsList');
const emptyState = document.getElementById('emptyState');
const contactCount = document.getElementById('contactCount');
const formTitle = document.getElementById('formTitle');

let isEditMode = false;
let editingContactId = null;

document.addEventListener('DOMContentLoaded', function() {
    loadContacts();
    setupEventListeners();
});

function setupEventListeners() {
    addContactBtn.addEventListener('click', showAddForm);
    cancelBtn.addEventListener('click', hideForm);
    
    // Add second cancel button listener
    const cancelBtn2 = document.getElementById('cancelBtn-2');
    if (cancelBtn2) {
        cancelBtn2.addEventListener('click', hideForm);
    }
    
    contactForm.addEventListener('submit', handleFormSubmit);
}

function showAddForm() {
    isEditMode = false;
    formTitle.textContent = 'Add New Contact';
    contactForm.reset();
    contactFormContainer.classList.remove('hidden');
    contactFormContainer.setAttribute('aria-hidden', 'false');
    contactFormContainer.scrollIntoView({ behavior: 'smooth' });
}

function hideForm() {
    contactFormContainer.classList.add('hidden');
    contactFormContainer.setAttribute('aria-hidden', 'true');
    contactForm.reset();
    isEditMode = false;
    editingContactId = null;
}

async function handleFormSubmit(e) {
    e.preventDefault();
    
    var formData = new URLSearchParams();
    formData.append('name', document.getElementById('name').value);
    formData.append('email', document.getElementById('email').value);
    formData.append('phone', document.getElementById('phone').value);
    formData.append('address', document.getElementById('address').value);
    
    var url = API_URL;
    var method = 'POST';
    
    if (isEditMode) {
        method = 'PUT';
        url = API_URL + '?id=' + editingContactId;
    }
    
    try {
        var response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: formData.toString()
        });
        
        if (response.ok) {
            showNotification(isEditMode ? 'Contact updated!' : 'Contact added!', 'success');
            hideForm();
            loadContacts();
        } else {
            showNotification('Operation failed', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Network error', 'error');
    }
}

async function loadContacts() {
    try {
        const response = await fetch(API_URL);
        const contacts = await response.json();
        displayContacts(contacts);
        updateContactCount(contacts.length);
    } catch (error) {
        console.error('Error loading contacts:', error);
    }
}

function displayContacts(contacts) {
    if (contacts.length === 0) {
        contactsList.style.display = 'none';
        emptyState.style.display = 'block';
        return;
    }
    
    emptyState.style.display = 'none';
    contactsList.style.display = 'grid';
    
    let html = '';
    for (let i = 0; i < contacts.length; i++) {
        const c = contacts[i];
        html += '<div class="contact-card glass">';
        html += '<div class="contact-avatar">' + getInitials(c.name) + '</div>';
        html += '<div class="contact-info">';
        html += '<h3>' + escapeHtml(c.name) + '</h3>';
        if (c.email) html += '<p>📧 ' + escapeHtml(c.email) + '</p>';
        if (c.phone) html += '<p>📱 ' + escapeHtml(c.phone) + '</p>';
        if (c.address) html += '<p>📍 ' + escapeHtml(c.address) + '</p>';
        html += '</div>';
        html += '<div class="contact-actions">';
        html += '<button class="btn-icon btn-edit" onclick="editContact(\'' + c.id + '\')">✏️</button>';
        html += '<button class="btn-icon btn-delete" onclick="deleteContact(\'' + c.id + '\')">🗑️</button>';
        html += '</div></div>';
    }
    contactsList.innerHTML = html;
}

async function editContact(id) {
    try {
        const response = await fetch(API_URL + '?id=' + id);
        const contact = await response.json();
        
        isEditMode = true;
        editingContactId = id;
        
        formTitle.textContent = 'Edit Contact';
        document.getElementById('name').value = contact.name || '';
        document.getElementById('email').value = contact.email || '';
        document.getElementById('phone').value = contact.phone || '';
        document.getElementById('address').value = contact.address || '';
        
        contactFormContainer.classList.remove('hidden');
        contactFormContainer.setAttribute('aria-hidden', 'false');
        contactFormContainer.scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error:', error);
        showNotification('Failed to load contact', 'error');
    }
}

async function deleteContact(id) {
    if (!confirm('Delete this contact?')) return;
    
    try {
        const response = await fetch(API_URL + '?id=' + id, { method: 'DELETE' });
        if (response.ok) {
            showNotification('Contact deleted!', 'success');
            loadContacts();
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function updateContactCount(count) {
    contactCount.textContent = count;
}

function getInitials(name) {
    return name.split(' ').map(function(w) { return w[0]; }).join('').toUpperCase().substring(0, 2);
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function showNotification(message, type) {
    const notification = document.createElement('div');
    notification.className = 'notification notification-' + type;
    notification.textContent = message;
    document.body.appendChild(notification);
    
    setTimeout(function() { notification.classList.add('show'); }, 100);
    setTimeout(function() {
        notification.classList.remove('show');
        setTimeout(function() { notification.remove(); }, 300);
    }, 3000);
}