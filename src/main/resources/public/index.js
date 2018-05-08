let contactsAll, tablerow, id, firstname, lastname, codename, phonenr

function sendGetRequest () {
  let xhr = new XMLHttpRequest()
  xhr.open('GET', 'contacts', true)
  xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      updateContacts(xhr.responseText)
    }
  }
  xhr.send(true)
}

function updateContacts (replyString) {
  contactsAll = JSON.parse(replyString)
  for (let i = 0; i < contactsAll.query.length; i++) {
    id = contactsAll.query[i].id
    firstname = contactsAll.query[i].fname
    lastname = contactsAll.query[i].lname
    codename = contactsAll.query[i].code
    phonenr = contactsAll.query[i].phone
    tablerow = '<tr><th scope="row">' + id + '</th><td>' + firstname + '</td><td>' +
            lastname + '</td><td>' + codename + '</td><td>' + phonenr + '</td></tr>'
    document.getElementById('databasequery').insertAdjacentHTML('beforeend', tablerow)
  }
}

function addContact (newContactString, newContactQueryString) {
  let xhr = new XMLHttpRequest()
  xhr.open('POST', 'contacts/add', true)
  xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // If adding new contact was successful then use updateContacts to append new contact to the end of the table
      updateContacts(newContactQueryString)
    }
  }
  xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8')
  xhr.send(newContactString)
}

document.addEventListener('DOMContentLoaded', function () {
  sendGetRequest() // As soon as DOM is loaded send request to fetch contacts
  document.getElementById('newcontactbtn').addEventListener('click', function () {
    let newContact = {
      'fname': document.querySelector('#fname').value,
      'lname': document.querySelector('#lname').value,
      'code': document.querySelector('#codename').value,
      'phone': document.querySelector('#phonenumber').value,
      'id': id + 1
    }
    let newContactQuery = newContact
    newContactQuery['id'] = id + 1
    newContactQuery = JSON.stringify({ 'query': [newContactQuery] })
    newContact = JSON.stringify(newContact)
    if (!(newContact.fname === '' || newContact.lname === '' || newContact.code === '' || newContact.phone === '')) {
      addContact(newContact, newContactQuery)
    }
  })
})
