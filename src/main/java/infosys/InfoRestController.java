package infosys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/contacts")
class InfoRestController{

    @RequestMapping(method = RequestMethod.GET)
	String ReadContactsRest()throws Exception {
        return InfosysData.readContactsFromDB();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	String AddContactRest(@RequestBody String contact)throws Exception {
		return InfosysData.addContact(contact);
	}
}