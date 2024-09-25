package lt.techin;

import lt.techin.philatelist.Philatelist;
import lt.techin.philatelist.PostStamp;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PhilatelistImpl implements Philatelist {

    private HashSet<PostStamp> postStamps;

    public PhilatelistImpl() {
        this.postStamps = new HashSet<>();
    }

    @Override
    public void addToCollection(PostStamp postStamp) {
        if (postStamp == null || postStamp.getName() == null || postStamp.getName().isEmpty()) {
            throw new IllegalArgumentException();
        }
        postStamps.add(postStamp);
    }

    @Override
    public int getNumberOfPostStampsInCollection() {
        return postStamps.size();
    }

    @Override
    public void printAllPostStampNames() {
        postStamps.stream().sorted(Comparator.comparing(PostStamp::getName)).forEach(n -> System.out.println(n.getName()));
    }

    @Override
    public void printPostStampsWithPriceGreaterThan(double v) {
        postStamps.stream()
                .filter(n -> n.getMarketPrice() > v)
                .sorted(Comparator.comparing(PostStamp::getName))
                .forEach(n -> System.out.println(n.getName()));
    }

    @Override
    public boolean isPostStampInCollection(PostStamp postStamp) {
        return postStamps.contains(postStamp);
    }

    @Override
    public boolean isPostStampWithNameInCollection(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return postStamps.stream().anyMatch(n -> n.getName().equals(name));
    }

    @Override
    public double calculateTotalMarketPrice() {
        return postStamps.stream().mapToDouble(PostStamp::getMarketPrice).sum();
    }

    @Override
    public double getAveragePostStampPrice() {
        return postStamps.stream().mapToDouble(PostStamp::getMarketPrice).average().orElse(0.0);
    }

    @Override
    public PostStamp getTheMostExpensivePostStampByMarketValue() {
        return postStamps.stream().max(Comparator.comparingDouble(PostStamp::getMarketPrice)).orElse(null);
    }

    @Override
    public List<PostStamp> findPostStampsByNameContaining(String nameFragment) {
        if (nameFragment.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return postStamps.stream().filter(n -> n.getName().contains(nameFragment)).collect(Collectors.toList());
    }

    @Override
    public List<PostStamp> getSortedPostStampsByName() {
        return postStamps.stream().sorted(Comparator.comparing(PostStamp::getName)).collect(Collectors.toList());
    }
}
