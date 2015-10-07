/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.model.Position;
import org.apache.struts.validator.ValidatorForm;

import java.util.List;

/**
 * Created: 25.10.2007 || 17:43:09
 *
 * @author A.Zainullina
 */
public class VacanciesForm extends ValidatorForm {
    private List<Position> vacancies;


    public List<Position> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Position> vacancies) {
        this.vacancies = vacancies;
    }
}
