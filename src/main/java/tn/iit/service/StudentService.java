package tn.iit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tn.iit.dto.StudentDto;

@Service
public class StudentService {
	private List<StudentDto> students = new ArrayList<>();

	public StudentService() {
		students.add(new StudentDto(1, "Eya", 'F'));
		students.add(new StudentDto(2, "Amine", 'M'));
	}
 
	public void save(StudentDto studentDto) {
		students.add(studentDto);
	}
	public void delete(int id) {
		students.remove(new StudentDto(id, null, null));
	}

	public List<StudentDto> findAll() {
		return students;
	}
}
