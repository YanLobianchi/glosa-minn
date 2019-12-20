package br.com.zgsolucoes.glosaminn.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import groovy.transform.CompileStatic

@CompileStatic
class CSVReader {
    static List<Map<String, String>> read(File file) throws JsonProcessingException, IOException {
        List<Map<String, String>> response = new LinkedList<Map<String, String>>()
        CsvMapper mapper = new CsvMapper()
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',' as char).withEscapeChar('\\' as char).withLineSeparator('\n')
        MappingIterator<Map<String, String>> iterator = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(file)
        while (iterator.hasNext()) {
            response.add(iterator.next())
        }
        return response
    }
}
