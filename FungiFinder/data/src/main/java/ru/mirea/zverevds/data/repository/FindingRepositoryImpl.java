package ru.mirea.zverevds.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.zverevds.data.storage.FindingStorage;
import ru.mirea.zverevds.data.models.Finding;
import ru.mirea.zverevds.domain.repository.FindingRepository;

public class FindingRepositoryImpl implements FindingRepository {

    private final FindingStorage sharedPrefFindingStorage;

    public FindingRepositoryImpl(FindingStorage sharedPrefFindingStorage) {
        this.sharedPrefFindingStorage = sharedPrefFindingStorage;
    }

    public boolean saveFinding (ru.mirea.zverevds.domain.models.Finding finding){
        sharedPrefFindingStorage.saveToCollection(mapToStorage(finding));
        return true;
    }

    public ru.mirea.zverevds.domain.models.Finding getFinding(int id){
        Finding finding = sharedPrefFindingStorage.get(id);
        return mapToDomain(finding);
    }

    private Finding mapToStorage(ru.mirea.zverevds.domain.models.Finding finding) {
        int id = finding.getId();
        String mushroomName = finding.getMushroomName();
        String location = finding.getLocation();
        String photoPath = finding.getPhotoPath();

        return new Finding(id, mushroomName, location, photoPath);
    }

    private ru.mirea.zverevds.domain.models.Finding mapToDomain(Finding finding) {
        int id = finding.getId();
        String mushroomName = finding.getMushroomName();
        String location = finding.getLocation();
        String photoPath = finding.getPhotoPath();

        return new ru.mirea.zverevds.domain.models.Finding(id, mushroomName, location, photoPath);
    }

    @Override
    public boolean saveFindingToCollection(ru.mirea.zverevds.domain.models.Finding finding) {
        sharedPrefFindingStorage.saveToCollection(mapToStorage(finding));
        return true;
    }

    @Override
    public boolean editFindingById(int id, String newLocation) {
        return true;
    }

    @Override
    public boolean deleteFindingById(int id) {
        return true;
    }

    @Override
    public List<ru.mirea.zverevds.domain.models.Finding> getFindingsCollection() {
        List<ru.mirea.zverevds.domain.models.Finding> findings = new ArrayList<>();
        findings.add(mapToDomain(new Finding(1, "Шампиньон", "Лес near home", "photo1.jpg")));
        findings.add(mapToDomain(new Finding(2, "Мухомор", "Парк", "photo2.jpg")));
        return findings;
    }
}
