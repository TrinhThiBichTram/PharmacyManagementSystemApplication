package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.model.MedicalInstrument;
import com.ttbtram.pharmacyManagementSystem.model.Medicine;
import com.ttbtram.pharmacyManagementSystem.model.Product;
import com.ttbtram.pharmacyManagementSystem.repository.GroupProductRepository;
import com.ttbtram.pharmacyManagementSystem.repository.MedicalInstrumentRepository;
import com.ttbtram.pharmacyManagementSystem.repository.MedicineRepository;
import com.ttbtram.pharmacyManagementSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GroupProductRepository groupProductRepository;
    private final MedicineRepository medicineRepository;
    private final MedicalInstrumentRepository medicalInstrumentRepository;

    // Lấy tất cả các Product
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    // Thêm một Medicine mới
    public Medicine addMedicine(Medicine medicine) {
        if (medicineRepository.existsByNameAndManufacturer(medicine.getName(),medicine.getManufacturer())) {
            throw new AlreadyExistsException(medicine.getName() +  " đã tồn tại");
        }
        return medicineRepository.save(medicine);
    }

    // Thêm một MedicalInstrument mới
    public MedicalInstrument addMedicalInstrument(MedicalInstrument medicalInstrument) {
        if (medicalInstrumentRepository.existsByNameAndManufacturer(medicalInstrument.getName(),medicalInstrument.getManufacturer())) {
            throw new AlreadyExistsException(medicalInstrument.getName() +  " đã tồn tại");
        }
        return medicalInstrumentRepository.save(medicalInstrument);
    }

    // Cập nhật Medicine
    public Medicine updateMedicine(Long id, Medicine medicine) {
        return medicineRepository.findById(id)
                .map(medicine1 -> {
                    medicine1.setName(medicine.getName());
                    medicine1.setUnit(medicine.getUnit());
                    medicine1.setPacket(medicine.getPacket());
                    medicine1.setManufacturer(medicine.getManufacturer());
                    medicine1.setManufacturingCountry(medicine.getManufacturingCountry());
                    medicine1.setImage(medicine.getImage());
                    medicine1.setStatus(medicine.getStatus());
                    medicine1.setPrice(medicine.getPrice());
                    medicine1.setRegistrationNumber(medicine.getRegistrationNumber());
                    medicine1.setMainActiveIngredient(medicine.getMainActiveIngredient());
                    medicine1.setMedicationDosage(medicine.getMedicationDosage());
                    medicine1.setPreserve(medicine.getPreserve());
                    GroupProduct newGroupProduct = groupProductRepository.findById(medicine.getGroupProduct().getId())
                            .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm hàng hóa với ID: " + id));
                    medicine1.setGroupProduct(newGroupProduct);
                    return medicineRepository.save(medicine1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thuốc có Id: " + id));
    }


    // Cập nhật MedicalInstrument
    public MedicalInstrument updateMedicalInstrument(Long id, MedicalInstrument medicalInstrument) {
        return medicalInstrumentRepository.findById(id)
                .map(medicalInstrument1 -> {
                    medicalInstrument1.setName(medicalInstrument.getName());
                    medicalInstrument1.setUnit(medicalInstrument.getUnit());
                    medicalInstrument1.setPacket(medicalInstrument.getPacket());
                    medicalInstrument1.setManufacturer(medicalInstrument.getManufacturer());
                    medicalInstrument1.setManufacturingCountry(medicalInstrument.getManufacturingCountry());
                    medicalInstrument1.setImage(medicalInstrument.getImage());
                    medicalInstrument1.setStatus(medicalInstrument.getStatus());
                    medicalInstrument1.setPrice(medicalInstrument.getPrice());
                    medicalInstrument1.setSpecification(medicalInstrument.getSpecification());
                    medicalInstrument1.setCirculationNumber(medicalInstrument.getCirculationNumber());
                    GroupProduct newGroupProduct = groupProductRepository.findById(medicalInstrument.getGroupProduct().getId())
                            .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm hàng hóa với ID: " + id));
                    medicalInstrument1.setGroupProduct(newGroupProduct);
                    return medicalInstrumentRepository.save(medicalInstrument1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy dụng cụ y tế có Id: " + id));
    }

    
    // Tìm kiếm theo id
    public Product getProductById(Long productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm có Id :" +productID));
    }
    // Tìm kiếm theo name
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

}
