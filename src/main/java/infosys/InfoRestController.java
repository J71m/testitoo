package infosys;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
class InfoRestController{
	

    @RequestMapping("/tere")
    String tervitus2(String eesnimi){
        return "Tere, "+eesnimi;
    }

    @RequestMapping("/")
    String rootFolder(){
        return "page!";
    }

    @RequestMapping(method = RequestMethod.GET)
	String ReadContactsRest()throws Exception {
        return InfosysData.readContactsFromDB();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	String AddContactRest(@RequestBody String contact)throws Exception {
		return InfosysData.addContact(contact);
	}
}
