package com.example.tasktracker.service.card;

import com.example.tasktracker.exceptions.ResourceNotFoundException;
import com.example.tasktracker.model.Board;
import com.example.tasktracker.model.Card;
import com.example.tasktracker.repository.card.CardRepository;
import com.example.tasktracker.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public void createCard(Card Card) {
        cardRepository.save(Card);
    }

    @Override
    public Card findCardById(Integer id) throws ResourceNotFoundException {
        return cardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Didn't find card by id: " + id));
    }

    @Override
    public void updateCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Integer id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Card takeCard(Integer cardId, Integer userId) throws ResourceNotFoundException {
        Card card = findCardById(cardId);
        card.setAssignee(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Didn't find user by id: " + userId)));

        moveCardToNextList(card);

        return cardRepository.save(card);
    }

    @Override
    public Card closeCard(Integer cardId) throws ResourceNotFoundException {
        Card card = findCardById(cardId);
        card.setAssignee(null);

        moveCardToNextList(card);
        cardRepository.save(card);
        return null;
    }

    public void moveCardToNextList(Card card) {
        com.example.tasktracker.model.List list = card.getList();
        Board board = list.getBoard();
        int listIndex = board.getLists().indexOf(list);
        if (listIndex != board.getLists().size() - 1) {
            com.example.tasktracker.model.List nextList = board.getLists().get(listIndex + 1);

            list.removeCard(card);
            nextList.addCard(card);

            card.setList(nextList);
        }
    }

    public void moveCardToPreviousList(Card card) {
        com.example.tasktracker.model.List list = card.getList();
        Board board = list.getBoard();
        int listIndex = board.getLists().indexOf(list);
        if (listIndex != 0) {
            com.example.tasktracker.model.List previousList = board.getLists().get(listIndex - 1);

            list.removeCard(card);
            previousList.addCard(card);

            card.setList(previousList);
        }
    }
}
