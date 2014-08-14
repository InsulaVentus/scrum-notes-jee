package no.agricolas.srumnotes.common;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.excel.ExcelGenerator;

import java.util.List;

/**
 * @author Simen Søhol
 */
public class GeneratorService implements SimpleGeneratorService {
    private ExcelGenerator excelGenerator = new ExcelGenerator();

    @Override
    public boolean createNotesFromSubtask(List<SubtaskNote> subtaskList, String path) {
        return excelGenerator.createNotesFromSubtask(subtaskList, path);
    }
}
