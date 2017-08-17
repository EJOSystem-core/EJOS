package cn.edu.sdut.softlab.converter;

import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.service.TeamFacade;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * @author huanlu
 *
 */
@ManagedBean(name = "teamConverter")
@FacesConverter(forClass = Team.class, value = "myTeamConverter")
public class TeamConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    TeamFacade teamService;

    @Override   
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Team team = teamService.findByName(value);
            return team.getId();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Team) {
            return value.toString();
        }
        return  null;
    }

}
