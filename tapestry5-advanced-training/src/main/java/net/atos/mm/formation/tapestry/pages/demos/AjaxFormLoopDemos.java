package net.atos.mm.formation.tapestry.pages.demos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.atos.mm.formation.tapestry.data.Phone;
import net.atos.mm.formation.tapestry.data.PhoneType;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class AjaxFormLoopDemos {
	
	public ValueEncoder<Phone> getEncoder(){
		return new ValueEncoder<Phone>() {

			public String toClient(Phone value) {
				return String.valueOf(phones.indexOf(value));
			}

			public Phone toValue(String clientValue) {
				return phones.get(Integer.parseInt(clientValue));
			}
		};
	}
	
	@Property
	@Persist
	private List<Phone> phones;
	
	@Property
	private Phone phone;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void setList(){
		if(phones == null){
			phones = new ArrayList<Phone>();
			phones.add(new Phone("0102030405",PhoneType.HOME));
		}
	}
	
	@OnEvent(EventConstants.ADD_ROW)
	public Object addPhone(){
		Phone newPhone = new Phone();
		phones.add(newPhone);
		return newPhone;
	}
	
	@OnEvent(EventConstants.REMOVE_ROW)
	public void removePhone(Phone newPhone){
		phones.set(phones.indexOf(newPhone), null);
	}
	
	@OnEvent(EventConstants.SUCCESS)
	public void submitForm(){
		phones.removeAll(Collections.singleton(null));
	}
}
