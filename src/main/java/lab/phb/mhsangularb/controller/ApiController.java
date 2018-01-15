package lab.phb.mhsangularb.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import lab.phb.mhsangularb.repo.MahasiswaRepo;
import lab.phb.mhsangularb.entity.Mahasiswa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication
.logout.SecurityContextLogoutHandler;
 

@RestController
public class ApiController {

	@Autowired
	private MahasiswaRepo mahasiswaRepo;

	@RequestMapping("/get-nama")
	public Map<String, Object> getNama() {
		Map<String, Object> result = new HashMap<>();
		result.put("nama", "tamami");
		return result;
	}

	@RequestMapping("/ambil-nama/{nama}")
	public Map<String, Object> ambilNama(
			@PathVariable("nama") String nama) {
		Map<String, Object> result = new HashMap<>();
		result.put("nama", nama);
		return result;
	}

	@RequestMapping("/set-nama")
	public Map<String, Object> setNama(
			@RequestParam("nama") String nama) {
		Map<String, Object> result = new HashMap<>();
		result.put("nama", nama);
		return result;
	}


	// -- aplikasi mahasiswa

	@RequestMapping("/daftar-mahasiswa")
	public List<Mahasiswa> getDaftarMahasiswa() {
		return mahasiswaRepo.findAll();
	}

	@RequestMapping(value = "/simpan", method = RequestMethod.POST)
	public void simpan(@RequestBody Mahasiswa mhs) {
		//System.out.println("nim : " + mhs.getNim());
		//System.out.println("nama : " + mhs.getNama());
		//System.out.println("jurusan : " + mhs.getJurusan());
		mahasiswaRepo.save(mhs);
	}

	@RequestMapping("/ambil-data-mhs/{nim}")
	public Mahasiswa getDataMhs(@PathVariable("nim") String nim) {
		//System.out.println("nim : " + nim);
		//return null;
		return mahasiswaRepo.findOneByNim(nim);
	}

	@RequestMapping(value = "/hapus/{nim}", method = RequestMethod.DELETE)
	public void hapus(@PathVariable("nim") String nim) {
		mahasiswaRepo.delete(nim);
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest req,
		HttpServletResponse resp){
		Authentication auth =
		SecurityContextHolder.getContext()
		.getAuthentication();
		new SecurityContextLogoutHandler().logout(req, resp, auth);
	}

	
}