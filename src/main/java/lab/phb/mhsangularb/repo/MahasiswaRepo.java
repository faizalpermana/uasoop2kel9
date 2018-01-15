package lab.phb.mhsangularb.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import lab.phb.mhsangularb.entity.Mahasiswa;

@Repository
public interface MahasiswaRepo 
	extends JpaRepository<Mahasiswa, String>
{

	Mahasiswa findOneByNim(String nim);
	
}