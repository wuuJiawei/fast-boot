package ${package};

import java.util.Date;
import com.w.core.model.BaseModel;

import lombok.Data;
import lombok.experimental.Accessors;
import org.beetl.sql.core.annotatoin.AutoID;


/**
 * ${comment}
 * \@see
 * \@since ${date(),"yyyy-MM-dd"}
 */
\@Data
\@Accessors(chain = true)
public class ${className} extends BaseModel{

@for(attr in attrs){
	@if(!isEmpty(attr.comment)){
	//${attr.comment}
	@}
	@if(attr.isId) {
    \@AutoID
	@}
	@if(isNotEmpty(attr.dictType)) {
    \@Dict(type="${attr.dictType}")
	@}
    private ${attr.type} ${attr.name} ;
	
@}


}
