# Contributing to MetroRide API

Thank you for your interest in contributing to MetroRide API! This document outlines our guidelines for contributing to the project.

## Branch Naming Rules

Follow these conventions when creating branches:

| Branch Type | Format | Example |
|-------------|--------|---------|
| Feature | `feature/<feature-name>` | `feature/add-payment-api` |
| Bug Fix | `bugfix/<bug-description>` | `bugfix/fix-station-search` |
| Hotfix | `hotfix/<issue-description>` | `hotfix/security-patch` |
| Release | `release/<version>` | `release/1.1.0` |
| Documentation | `docs/<description>` | `docs/update-readme` |

### Branch Naming Best Practices
- Use lowercase letters only
- Use hyphens (-) to separate words
- Keep names short but descriptive
- Include ticket/issue number if applicable (e.g., `feature/METRO-123-user-auth`)

---

## Commit Message Format

We follow the **Conventional Commits** specification for commit messages:

```
<type>(<scope>): <subject>

[optional body]

[optional footer]
```

### Types
| Type | Description |
|------|-------------|
| `feat` | A new feature |
| `fix` | A bug fix |
| `docs` | Documentation only changes |
| `style` | Code style changes (formatting, semicolons, etc.) |
| `refactor` | Code refactoring without feature changes |
| `test` | Adding or updating tests |
| `chore` | Maintenance tasks (build, dependencies, etc.) |

### Examples
```
feat(api): add metro station search endpoint
fix(timings): correct time zone calculation
docs(readme): update installation instructions
chore(deps): upgrade Spring Boot to 3.2.0
```

### Commit Message Guidelines
- Use imperative mood ("add" not "added" or "adds")
- Keep subject line under 50 characters
- Do not end subject line with a period
- Separate subject from body with a blank line
- Use body to explain what and why (not how)

---

## Merge Request Guidelines

### Before Creating a Merge Request
1. **Update your branch** with the latest changes from `main`
2. **Run all tests** locally and ensure they pass
3. **Build the project** using both Maven and Gradle
4. **Review your code** for any debugging statements or unnecessary comments

### Merge Request Checklist
- [ ] Branch follows naming conventions
- [ ] Code compiles without errors
- [ ] All tests pass
- [ ] Code follows project style guidelines
- [ ] Documentation updated (if applicable)
- [ ] Commit messages follow conventions

### Merge Request Description Template
```markdown
## Summary
Brief description of the changes

## Type of Change
- [ ] Feature
- [ ] Bug fix
- [ ] Documentation
- [ ] Refactoring

## Testing Done
Describe testing performed

## Related Issues
Closes #<issue-number>
```

### Review Process
1. Create a merge request from your feature branch to `main`
2. Request review from at least one team member
3. Address all review comments
4. Squash commits if necessary before merging
5. Delete the feature branch after successful merge

---

## Questions?

If you have questions about contributing, please reach out to the development team.
