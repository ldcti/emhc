package com.emhc.validator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.emhc.dto.AnswerDTO;
import com.emhc.dto.BeanMethodAnnotation;

@Component
public class AssessmentValidator implements Validator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AnswerDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.debug("Assessment Validating {} started", target);
		AnswerDTO form = (AnswerDTO) target;
		validateAnswerDTO(errors, form);

		// input string conatains numeric values only
	}

	private void validateAnswerDTO(Errors errors, AnswerDTO form) {

/*		try {
			Class<?> c = Class.forName(getClassName());
			Object t = c.newInstance();

			Method[] allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
				String mname = m.getName();
				if (!mname.startsWith("getButton") || (m.getGenericReturnType() != boolean.class)) {
					continue;
				}
				Type[] pType = m.getGenericParameterTypes();
				if ((pType.length != 1) || Locale.class.isAssignableFrom(pType[0].getClass())) {
					continue;
				}

				out.format("invoking %s()%n", mname);
				try {
					m.setAccessible(true);
					Object o = m.invoke(t, new Locale(args[1], args[2], args[3]));
					out.format("%s() returned %b%n", mname, (Boolean) o);

					// Handle any exceptions thrown by method to be invoked.
				} catch (InvocationTargetException x) {
					Throwable cause = x.getCause();
					err.format("invocation of %s failed: %s%n", mname, cause.getMessage());
				}
			}

			// production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (InstantiationException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		}
	}*/
		try {
				Class<?> c = Class.forName(getClassName());
				Method[] methods = c.getMethods();
				
			
				int i;
				
			    List<Method> methodList = new ArrayList<>();  
			    // 过滤带有注解的Field  
			    for(Method mm:methods){  
			        if(mm.getAnnotation(BeanMethodAnnotation.class)!=null){  
			            methodList.add(mm);  
			            System.out.println("method is -----\n"+mm.getName());
			        }  
			    }  
			    // 这个比较排序的语法依赖于java 1.8  
			    methodList.sort(Comparator.comparingInt( m -> m.getAnnotation(BeanMethodAnnotation.class).order()));  			
			    for( i=0; i<methodList.size(); i++){  
			        		 
				            System.out.println("final method is -----\n"+ methodList.get(i).getName());
			         
			    }  
			    
/*				SortedSet<Method> sortedmethods = new TreeSet<Method>(new MethodComparator());
				sortedmethods.addAll(Arrays.asList(tempmethods));
				
				for (j=0; j < sortedmethods.size(); j++){
					System.out.println(sortedmethods[i].getName());
					try{
							
							m.setAccessible(true);
							if (m.invoke(form) == null) {
								name = methods[i].getName();
								name.replace("", "getButton");
								System.out.println(" name is "+ name);
								String temp = "Assessment.select.error" + name;
								errors.rejectValue("button01", temp);

							} else if (methods[i].invoke(form).equals("Yes")) {

								name = methods[i].getName();
								name.replace("Button", "Answer");
								System.out.println("----------------------" + name);
								Method mm = c.getMethod(name, null);
								if (mm.invoke(form).equals(null)) {
									LOGGER.debug("email not in right format");
									errors.rejectValue("button01", "Assessment.selected.error");

								}
							
							}
						}catch (InvocationTargetException x) {
								Throwable cause = x.getCause();
							}
				}*/
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		
			/*
			 * if (form.getButton01() == null) { errors.rejectValue("button01",
			 * "Assessment.select.error");
			 * 
			 * } else {
			 * 
			 * System.out.println("-------button is --------------" +
			 * form.getButton01()); if
			 * (form.getButton01().equalsIgnoreCase("Yes")) {
			 * 
			 * System.out.println("-------button is --------------" +
			 * form.getButton01().equalsIgnoreCase("Yes"));
			 * System.out.println("----answer is --------"+ form.getAnswer1());
			 * } if (form.getAnswer1().isEmpty()) {
			 * System.out.println("-------button is here--------------");
			 * LOGGER.debug("email not in right format");
			 * errors.rejectValue("button01", "Assessment.selected.error");
			 * 
			 * } }
		}*/

	}

	private String getClassName() {

		// Do appropriate stuff here to find out the classname

		return "com.emhc.dto.AnswerDTO";
	}
}