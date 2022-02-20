package sections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sections{
    private List<Section> sectionList = new ArrayList<>();

    public void addSection(Section newSection){
        sectionList.add(newSection);
    }

    public List<Section> getList(){
        return sectionList;
    }

    public int searchSection(String name){
        for(Section i: sectionList){
            if(Objects.equals(i.section, name)){
                return sectionList.indexOf(i);
            }
        }

        return -1;
    }
}