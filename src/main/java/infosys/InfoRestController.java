package infosys;
import org.springframework.web.bind.annotation.*;

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