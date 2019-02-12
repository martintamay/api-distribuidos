package com.sma.delivery.service.comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.comments.CommentsDaoImpl;
import com.sma.delivery.dao.comments.ICommentsDao;
import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.dao.users.IUserDao;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.dto.comments.CommentDTO;
import com.sma.delivery.dto.comments.CommentResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class CommentsServiceImpl extends BaseServiceImpl<CommentDTO, CommentsDomain, CommentsDaoImpl, CommentResult> implements ICommentsService {
	@Autowired
	private ICommentsDao commentsDao;
	
	@Autowired
	private IEstablishmentsDao establishmentsDao;
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'commentsA_' + #comment.id", condition = "#dto.id!=null")
	public CommentDTO save(CommentDTO dto) {
		final CommentsDomain domain = convertDtoToDomain(dto);
		final CommentsDomain commentsDomain = commentsDao.save(domain);
		final CommentDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("commentsA_" + domain.getId(), newDto);
		}
		return convertDomainToDto(commentsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'commentsA_' + #id")
	public CommentDTO getById(Integer id) {
		final CommentsDomain domain = commentsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public CommentResult getAll() {
		final List<CommentDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findAll()) {
			final CommentDTO dto = convertDomainToDto(domain);
			comments.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("commentsA_" + dto.getId(), dto);
			}
		}
		final CommentResult commentsResult = new CommentResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}

	@Override
	protected CommentDTO convertDomainToDto(CommentsDomain domain) {
		final CommentDTO dto = new CommentDTO();
		dto.setId(domain.getId());
		dto.setTitle(domain.getTitle());
		dto.setContent(domain.getContent());
		dto.setDeleted(domain.getDeleted());
		dto.setEstablishmentId(domain.getEstablisment().getId());
		dto.setUserId(domain.getUser().getId());

		return dto;
	}

	@Override
	protected CommentsDomain convertDtoToDomain(CommentDTO dto) {
		final CommentsDomain domain = new CommentsDomain();
		domain.setId(dto.getId());
		domain.setTitle(dto.getTitle());
		domain.setContent(dto.getContent());
		domain.setDeleted(dto.getDeleted());
		domain.setEstablisment(establishmentsDao.getById(dto.getEstablishmentId()));
		domain.setUser(userDao.getById(dto.getUserId()));
		return domain;
	}

	@Override
	@Transactional
	public CommentResult get(Integer page, Integer tamPag) {
		final List<CommentDTO> clientss = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findByParams(page, tamPag)) {
			final CommentDTO client = convertDomainToDto(domain);
			clientss.add(client);
		}

		final CommentResult clientResult = new CommentResult();
		clientResult.setComments(clientss);
		return clientResult;
		}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'commentsA_' + #dto.id")
	public CommentDTO update(CommentDTO dto) {
		final CommentsDomain clientDomain = convertDtoToDomain(dto);
		final CommentsDomain client = commentsDao.update(clientDomain);
		final CommentDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("commentsA_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'commentsA_' + #dto.id")
	public void delete(CommentDTO dto) {
		final CommentsDomain commentsDomain = convertDtoToDomain(dto);
		commentsDao.delete(commentsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_com' + #text")
	public CommentResult find(String text, Integer page, Integer size) {
		final List<CommentDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.find(text,page, size)) {
			final CommentDTO user = convertDomainToDto(domain);
			comments.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("commentsA_" + user.getId(), user);
			}
		}

		final CommentResult commentsResult = new CommentResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}
	@Override
	@Transactional
	public CommentResult getAll(Integer page,Integer size) {
		final List<CommentDTO> comments = new ArrayList<>();
		for (CommentsDomain domain : commentsDao.findAll(page,size)) {
			final CommentDTO dto = convertDomainToDto(domain);
			comments.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("commentsA_" + dto.getId(), dto);
			}
		}
		final CommentResult commentsResult = new CommentResult();
		commentsResult.setComments(comments);
		return commentsResult;
	}

}

